import React, {useState} from "react";
import logo from'./logo.png';
// import axios from 'axios';
import { HiEye, HiEyeOff } from "react-icons/hi";
import './auth.css';

function Auth() {
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [show, setShow] = useState(false);
 
    const handleClick = (e) => {
       e.preventDefault()
       setShow(!show);
    }
    // const handleSubmit = values => {
    //     axios.post('http://localhost:8080/v1/api/user', values)
    //         .then(res => {
    //             const { data } = res;
    //             if (data) {
    //                 history.push('/create');
    //             }
    //         })
    // }

    // const validations = yup.object().shape({
    //     email: yup.string().email().required(),
    //     password: yup.string().min(8).required()
    // })
 
    return (
        <div className="auth">          
          <div className="game-logo">
            <img src={logo} alt="logo" />
          </div>
          <div className="auth-card">
                <div className="input-name">
                    <input 
                        type="text"
                        placeholder="Nome"
                    /> 
                </div> 
              <div className="input-email">
                    <input 
                        type="email"
                        placeholder="Email"
                        value={email}
                        onChange={e => setEmail(e.target.value)}
                    /> 
              </div>
              <div className="input-password">
                    <input 
                        placeholder="Senha"
                        type={show ? "text" : "password"}
                        value={password}
                        onChange={e => setPassword(e.target.value)}
                    /> 
                <div className="auth-eye">
                    {show ? (
                        <HiEye
                            size={20}
                            onClick={handleClick}
                        />
                    ) : (
                        <HiEyeOff
                            size={20}
                            onClick={handleClick}
                        />
                    )}
                </div>
            </div>
    
              <div className="button1">
                    <button className="signup-button" type="submit">Cadastrar</button>          
              </div>
              <div className="button2">
                    <button className="login-button" type="submit">Entrar</button>
              </div>
          </div>
        </div>
    )
}
export default Auth;