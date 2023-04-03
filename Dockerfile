FROM postgres:latest
VOLUME /var/lib/postgresql/data


# the command to run the docker file
# docker build -t mypet_db .

# the command to run the docker image
# docker run -d -p 5400:5432 --name mypet_db --env-file .env mypet_db

