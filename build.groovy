// generate actual message files

def dir = new File("output");
dir.deleteDir()
dir.mkdir()

new File("output/patron.css").text = new File("patron.css").text

def xml = new XmlSlurper().parse("messages.xml")

def dt = new Date()
dt = "${dt.year+1900}Q${1+((int)dt.month/3)}"

def all = [:];

def slots = xml.quarter.each { q ->
    def messageCounts = [];
    all[q.@time.text()] = messageCounts

    int s = 0;

    q.slot.each { slot ->
        for (int i=0; i<((slot.@weight?.text() ?: 1) as int); i++) {// repeat for each weight of each slot
            int m = 0;
            slot.message.each { msg ->
                generate(msg, "output/${q.@time}-${s}_${m}.html");
                m++;
            }
            messageCounts << m;
            s++;
        }
    }
}

new File("output/message.html").text = new File("message.html").text.replace("{DATA}",toHash(all));

def toHash(m) {
  "{"+m.collect { k,v -> " '${k}':$v " }.join(',')+"}"
}

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
<link rel="stylesheet" type="text/css" href="patron.css">
<body style="margin:0; overflow:hidden">
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
