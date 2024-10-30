FROM fabiocicerchia/nginx-lua:1.27.2-debian12.7 AS nginx

RUN apt-get update && apt-get install -y sudo

COPY shutdown.sh /var/data/shutdown.sh
RUN chmod +x /var/data/shutdown.sh
RUN echo "nginx ALL=(ALL) NOPASSWD: /var/data/shutdown.sh" >> /etc/sudoers

EXPOSE 80