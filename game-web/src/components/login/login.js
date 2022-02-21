import React from 'react';
import './login.scss';

export default function Login() {

  return(
    <div className="login">
      <h1>aqui vai o logo</h1>
      <form>
        <Field
          component= {Input}
        />
        <div>
          <button type="submit">LOGIN</button>
        </div>
      </form>
    </div>
  )
}