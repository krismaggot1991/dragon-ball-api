# Etapa de construcción
FROM node:17-alpine as build

WORKDIR /app
COPY . .
RUN npm install
RUN npm run build --prod

# Etapa de producción
FROM nginx:1.25.1-alpine

# Copiar los archivos estáticos generados en la etapa de construcción
COPY --from=build /app/dist/heroes-app /usr/share/nginx/html

# Exponer el puerto 80 para que sea accesible desde el exterior
EXPOSE 80

# Comando para iniciar el servidor Nginx en primer plano
CMD ["nginx", "-g", "daemon off;"]