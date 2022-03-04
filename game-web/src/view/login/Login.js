import React, { Component } from 'react';
import { connect } from 'react-redux';
import { withStyles } from '@material-ui/core';
import Container from '@material-ui/core/Container';
import { Button } from '@material-ui/core';
import { TextField } from '@material-ui/core/TextField';
import Typography from '@material-ui/core/Typography';
import Link from '@material-ui/core/Link'; 
import { lightGreen } from '@material-ui/core/colors';
import {login, changeValue} from '../../store/actions/auth.action';

const ColorButton = withStyles(theme => ({
  root: {
    color: '#fff',
    backgroundColor: lightGreen[300],
    '&hover' : {
      backgroundColor: lightGreen[700]
    }
  }
}))(Button);

export class Login extends Component {

  login = () => {
    const { credentials } = this.props;
    this.props.login(credentials).then(() => {

    });
  }
  render() {
    return (
      <div>
        <Container component="main" maxWidth="xs">
          <div className="mt-3 mt-md-3">
            <div className="text-center">
              <img src="../../../assets/logo_alterada.png"/>
              <Typography className="mt-3" component="h1" variant="h6">
                Pense e Resonda
              </Typography>
            </div>
            <div className='mt-4'>
              <TextField 
                variant="outlined" 
                margin="normal"
                required
                fullWidth
                id="email"
                label="Email"
                name="username"
                type="email"
                value={ this.props.credentials.username}
                onChange={ (text) => this.props.changeValue({username: text.target.value})}
                />

              <TextField 
                variant="outlined" 
                margin="normal"
                required
                fullWidth
                id="password"
                label="Senha"
                name="password"
                type="password"
                value={ this.props.credentials.passsword}
                onChange={ (text) => this.props.changeValue({password: text.target.value})}
                />

              <Button
                type="button"
                variant="contained"
                fullWidth
                color="primary"
                size="large"
                className="mb-3 mb-md-4 mt-4"
                onClick={() => this.login}
              > Entrar </Button>

              <Link href="/cadastrar">
                <ColorButton
                type="button"
                fullWidth
                size="large"
                variant="contained"
                className='mt-md-4'
                > Cadastrar </ColorButton>
              </Link>
            </div>
          </div>
        </Container>
      </div>
    )
  }
}

const mapStateToProps = (state) => ({
  credentials: state.authReducer.credentials,
});

const mapDispatchToProps = dispatch => ({
  login: (credentials) => dispatch(login(credentials)),
  changeValue: (value) => dispatch(changeValue(value))
});

export default connect(mapStateToProps, mapDispatchToProps)(Login)