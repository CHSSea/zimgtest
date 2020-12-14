# zimgtest
图片服务器
# zimgtest

### 1.安装zimg
docker pull iknow0612/zimg

### 2.启动
docker run -it -d -p 4869:4869 -v /zimgdata/:/zimg/bin/img --name my_zimg iknow0612/zimg sh app.sh

### 3.访问
http://ip:4869 