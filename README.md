This is a [Next.js](https://nextjs.org/) project bootstrapped with [`create-next-app`](https://github.com/vercel/next.js/tree/canary/packages/create-next-app).

## Orion Game

### Game service

```bash
cd game-api
cd src/main/docker
docker-compose up -d
cd ../../..
./mvnw compile quarkus:dev
```

### Game app


```bash
npm run dev
# or
yarn dev
```

[![Netlify Status](https://api.netlify.com/api/v1/badges/5d78451a-6ba3-4ec7-864f-9c6d5d43ca7f/deploy-status)](https://app.netlify.com/sites/apporiongame/deploys)

<https://apporiongame.netlify.app/>