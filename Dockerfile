FROM tomcat:10-jre17

EXPOSE 80

#서버 시간 설정
ENV TZ Asia/Seoul
ENV PATH /usr/bin:$PATH

#파이썬 설치
# Avoid prompts during package installations
ARG DEBIAN_FRONTEND=noninteractive

RUN apt-get update && \
    apt-get install -y --no-install-recommends \
    python3 python3-pip wget \
    && apt-get clean \
    && rm -rf /var/lib/apt/lists/*

COPY requirements.txt ./

RUN python3 -m pip install --no-cache-dir --upgrade pip \
  && python3 -m pip install --no-cache-dir -r requirements.txt

WORKDIR /usr/local/tomcat/conf
RUN rm ./server.xml
COPY ./server.xml ./

WORKDIR /usr/local/tomcat/webapps
COPY ./build/libs/ROOT.war ./