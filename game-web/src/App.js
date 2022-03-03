import React from 'react';
import ReactDOM from 'react-dom';
import { store } from './store/store';
import { createMuiTheme, ThemeProvider } from '@material-ui/core/styles';
import Button from '@mui/material/Button';
import Routes from './routes';
import './css/dashboard.css';

const theme = createMuiTheme ({
    palette: {
        primary: {
            main: '#ccff00'
        }
    }
});

function App() {
    return (
        <Provider store={store}>
            <ThemeProvider theme={theme}>
                <Routes/>
            </ThemeProvider>
        </Provider>
    )
}