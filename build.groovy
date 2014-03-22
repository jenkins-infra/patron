// generate actual message files

def dir = new File("output");
dir.deleteDir()
dir.mkdir()

def xml = new XmlSlurper().parse("messages.xml")

def dt = new Date()
dt = "${dt.year+1900}Q${1+((int)dt.month/3)}"

def slots = xml.slots.find{ it.@time==dt }

int s = 0;
def messageCounts = [];

if (slots!=null) {
  slots.slot.each { slot ->
    for (int i=0; i<((slot.@weight?.text() ?: 1) as int); i++) {// repeat for each weight of each slot
      int m = 0;
      slot.message.each { msg ->
        generate(msg, "output/message-${s}_${m}.html");
        m++;
      }
      messageCounts << m;
      s++;
    }
  }
}

new File("output/message.html").text = new File("message.html").text.replace("[1,1,2]",messageCounts.toString());


def generate(msg,name) {
  def caption = msg.caption.text()
  def link = msg.link.text()
  def blurb = msg.blurb.text()
  def logo = msg.logo.text()
  
  def linkText = link;
  if (linkText.startsWith("https://")) linkText=linkText.substring(8);
  if (linkText.startsWith("http://"))  linkText=linkText.substring(7);
  
  new File(name).text = """
<html>
<link rel="stylesheet" type="text/css" href="../patron.css">
<body style="margin:0">
  <div id="patron">
    <div style="float:right;">
      <a class=ourl href="${link}">
        <img class=logo src="${logo}">
      </a>
    </div>
    <div class=inner>
      <div class="color"><a href="${link}" id=ocaption class=ourl>${caption}</a></div>
      <div class="color"><a href="${link}" id=olink class=ourl>${linkText}</a></div>
      <div id=oblurb>${blurb}</div>
    </div>
  </div>
</body>
</html>
"""
}
