FROM tomcat:10-jre17

EXPOSE 8080

#서버 시간 설정
ENV TZ Asia/Seoul
ENV PATH /usr/bin:$PATH

#파이썬 설치
# Avoid prompts during package installations
ARG DEBIAN_FRONTEND=noninteractive

RUN apt-get update && \
    apt-get install -y --no-install-recommends \
    python3 python3-pip \
    && apt-get clean \
    && rm -rf /var/lib/apt/lists/*

COPY requirements.txt ./

RUN python3 -m pip install --no-cache-dir --upgrade pip \
  && python3 -m pip install --no-cache-dir -r requirements.txt