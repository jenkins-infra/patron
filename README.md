Patron of Jenkins
=================

This program defines a donation level to the Jenkins project, called "Patron of Jenkins," for donations at or above $750.
To show appreciation to patrons of Jenkins, we will allow them to display messages on our website in a non-obtrusive way.
See [Wiki page](https://wiki.jenkins-ci.org/display/JENKINS/Patron+of+Jenkins+program) for more details of the program.

This repository maintains the source code, [master data](messages.xml), and a program that generates the actual messages
that follow the specified format. Once the donation is confirmed, [Daniel Beck](https://github.com/daniel-beck) updates
the file to include your message.

This then gets processed by [Jenkins-on-Jenkins](https://ci.jenkins.io/job/Infra/job/patron/)
to be uploaded into the production server.


Wiki implementation
-------------------

[Jenkins Wiki](https://wiki.jenkins-ci.org/) shows patron messages through the "Page Templates" where the following code block is inserted:

```html
<script>
if (Math.random()*7<1) {
  document.write("<div align='center'><div style='width: 568px; color: #888; text-align:right; font-size:10px'>Message from a Patron of Jenkins</div><iframe src='https://jenkins-infra.github.io/patron/message.html' width=568 height=75 style='border: 1px solid #ccc; overflow:hidden'></iframe></div>");
}
</script>
```

Deployment
----------

1. `./build.groovy`
1. `git checkout gh-pages`
1. `cp output/* .`
1. `git commit -a`
1. `git push`
