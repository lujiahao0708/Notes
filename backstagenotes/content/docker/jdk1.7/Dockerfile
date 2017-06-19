FROM centos
MAINTAINER lujiahao
ADD jdk-7u80-linux-x64.tar.gz /usr/local
RUN mv /usr/local/jdk1.7.0_80 /usr/local/jdk1.7
ENV JAVA_HOME /usr/local/jdk1.7
ENV JRE_HOME /usr/local/jdk1.7/jre
ENV CLASSPATH .:$JAVA_HOME/jre/lib/rt.jar  :$JAVA_HOME/lib/dt.jar:$JAVA_HOME/lib/tools.jar
ENV PATH $JAVA_HOME/bin:$PATH
