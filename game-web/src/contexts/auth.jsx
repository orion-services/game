import React, { useState, useEffect, createContext} from 'react';

import { useNavigate } from 'react-router-dom';

import { api, createSession } from '../services/api';

export const AuthContext = createContext();

export const AuthProvider = ({ children }) => {
    const [user, setuser] = useState(null);

    const navigate = useNavigate();

    const [loading, setLoading] = useState(true);

    useEffect(() => {
        const recoveredUser = localStorage.getItem('user');
        const token = localStorage.getItem('token');

        if(recoveredUser && token){
            setuser(JSON.parse(recoveredUser));
            api.defaults.headers.Authorization = `Bearer ${token}`;
        }

        setLoading(false);
    }, []);

    const login = async (email, password) => {
        const response = await createSession(email, password);

        const loggedUser = response.data.user;
        const token = response.data.token;

        localStorage.setItem("user", JSON.stringify(loggedUser));
        localStorage.setItem("token", token);

        api.defaults.headers.Authorization = `Bearer ${token}`;

        setuser(loggedUser);
        navigate("/");
        
    };

    const logout = ( ) => {
        localStorage.removeItem("user");
        localStorage.removeItem("token");
        api.defaults.headers.Authorization = null;
        setuser(null);
        navigate("/login");
    };
    return (
    <AuthContext.Provider value={{authenticated: !!user, user, loading, login, logout}}>
        {children}
    </AuthContext.Provider>
    );
}