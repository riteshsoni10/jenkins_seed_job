FROM alpine:latest

LABEL MAINTAINER="Ritesh Kumar"
RUN apk --no-cache --update add jenkins git curl docker gettext -X http://dl-cdn.alpinelinux.org/alpine/edge/community && \
    curl -LO https://storage.googleapis.com/kubernetes-release/release/`curl -s https://storage.googleapis.com/kubernetes-release/release/stable.txt`/bin/linux/amd64/kubectl &&\
    chmod +x kubectl && \
    mv kubectl /usr/bin && \
    mkdir /root/.kube && \
    apk del curl

ARG http_port=8080

ARG HOME=/var/lib/jenkins

COPY client.crt client.key ca.crt config.template /root/.kube/
ENV KUBERNETES_CLUSTER_IP="192.168.99.101" 
ENV CA_CERTIFICATE="ca.crt"
ENV CLIENT_CERTIFICATE="client.crt"
ENV CLIENT_KEY="client.key"
ENV JENKINS_HOME ${HOME}
ENV USER jenkins

EXPOSE ${http_port}

CMD /bin/sh -c "envsubst \"`env | awk -F = '{printf \" \\\\$%s\", \$1}'`\" < /root/.kube/config.template > /root/.kube/config  && java -jar /usr/share/webapps/jenkins/jenkins.war"
