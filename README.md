Patron of Jenkins
=================

This program defines a donation level to the Jenkins project, called "Patron of Jenkins," for donations at or above $750.
To show appreciation to patrons of Jenkins, we will allow them to display messages on our website in a non-obtrusive way.
See [Wiki page](https://wiki.jenkins-ci.org/display/JENKINS/Patron+of+Jenkins+program) for more details of the program.

This repository maintains the source code, [master data](messages.xml), and a program that generates the actual messages
that follow the specified format. Once the donation is confirmed, [Lisa Wells](https://github.com/lisawells/) updates
the file to include your message.

This then gets processed by [Jenkins-on-Jenkins](https://ci.jenkins-ci.org/view/Infrastructure/job/infra_patron_messages/)
to be uploaded into the production server.


Wiki implementation
-------------------

[Jenkins Wiki](https://wiki.jenkins-ci.org/) shows patron messages through  [confluence-static-cache](https://github.com/kohsuke/confluence-static-cache/tree/jenkins) layer.
