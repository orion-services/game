import React, { useState, useContext} from "react";
import { AuthContext} from "../../contexts/auth";

import logo from'./logo.png';
// import axios from 'axios';
//import { HiEye, HiEyeOff } from "react-icons/hi";
import './auth.css';

function Auth() {
    const { authenticated, login } = useContext(AuthContext);
    // const [name, setName] = useState("");
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    //const [show, setShow] = useState(false);
    //const [isSignup, setIsSignup] = useState(false);
 
    const handleClick = (e) => {
       e.preventDefault()
    //    setShow(!show);
    console.log("submit", {email, password});
    login(email, password);
    }
 
    return (
        <div className="auth">       
        <p> {String(authenticated)}</p>   
          <div className="game-logo">
            <img src={logo} alt="logo" />
          </div>
            <form className="auth-form" onSubmit={handleClick}>
             {/* <div className="input-name">
                <input 
                    type="text"
                    placeholder="Nome"
                    id="name"
                    value={name}
                    onChange={e => setName(e.target.value)}
                /> 
              </div>  */}
              <div className="input-email">
                    <input 
                        type="email"
                        placeholder="Email"
                        id="email"
                        value={email}
                        onChange={e => setEmail(e.target.value)}
                    /> 
              </div>
              <div className="input-password">
                    <input 
                        placeholder="Senha"
                        // type={show ? "text" : "password"}
                        type="password"
                        id="password"
                        value={password}
                        onChange={e => setPassword(e.target.value)}
                    /> 
                {/* <div className="auth-eye">
                    { {show ? (
                        <HiEye
                            size={20}
                            onClick={handleClick}
                        />
                    ) : (
                        <HiEyeOff
                            size={20}
                            onClick={handleClick}
                        />
                    )} }
                </div> */}
            </div>
            
            {/* <div className="button1">
                <button className="signup-button" type="submit">Cadastrar</button>          
            </div> */}
            <div className="button2">
                <button className="login-button" type="submit">Entrar</button>
            </div>
              
              </form>
          </div>
          
      
    )
}
export default Auth;