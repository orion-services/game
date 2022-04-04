import React, { Fragment, useContext } from "react";

import { AuthContext } from "../../contexts/auth";

const Home = () => {
    const { authenticated, logout } = useContext(AuthContext);

    const handleLogout = () => {
        logout();
    }

    return (
        <Fragment>
            <h1>Página inicial</h1>
            <p>{String(authenticated)}</p>
            <button onClick={handleLogout}>Logout</button>
        </Fragment>
    );
};

export default Home;