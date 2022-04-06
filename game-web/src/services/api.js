import axios from "axios";

export const api = axios.create({ baseURL: "http://localhost:8080/game/api/v1/" });

export const createSession = async (email, password) => {
    return api.post("/playerlogin", 
    { email, password },
    { headers: { 
        "Content-Type": "application/json",
        "Access-Control-Allow-Origin": "*"
     } }
    );
};