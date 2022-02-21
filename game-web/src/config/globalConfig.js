import Axios from 'axios';
export const rootUrl = 'http://localhost/';
export const http = Axios.create({
    baseUrl: rootUrl
});