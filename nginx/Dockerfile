FROM fabiocicerchia/nginx-lua:1.27.2-debian12.7 AS nginx

COPY ./html /usr/share/nginx/html
COPY ./nginx.conf /etc/nginx/nginx.conf.template

RUN apt-get update && apt-get install -y sudo
RUN luarocks install lua-resty-http

COPY shutdown.sh /var/data/shutdown.sh
RUN chmod +x /var/data/shutdown.sh
RUN echo "nginx ALL=(ALL) NOPASSWD: /var/data/shutdown.sh" >> /etc/sudoers

EXPOSE 80