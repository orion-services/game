import { Http } from '../../config/globalConfig';
export const actionType = {
    GET_TOKEN: 'GET_TOKEN',
    LOGOUT: 'LOGOUT',
    LOADING: 'LOADING',
    SUCCESS: 'SUCCESS',
    ERROR: 'ERROR',
    CHANGE: 'CHANGE'
}
export const getToken = (token) => ({
    type: actionType.GET_TOKEN,
    token
});

export const removeToken = () => ({
    type: actionType.LOGOUT
});

export const loginSuccess = bool => ({
    type: actionType.SUCCESS,
    payload
});

export const loginError = (error) => ({
    type: actionType.ERROR,
    error
});

export const changeValue = (payload) => ({
    type: actionType.CHANGE,
    payload
});

export const loading = (bool, message = null) => ({
    type: actionType.LOADING,
    isLoading: {
        active: bool,
        message: message
    }
});

export const getUserToken = () => dispatch =>
    localStorage.getItem('access_token').then(res => {
        dispatch(loading(false));
        if (typeof res !== 'undefined') {
            dispatch(getToken(res));
        }
    });
export const setUserToken = (token) => dispatch => {
    localStorage.setItem('access_token', token);
    dispatch(loading(false));
    dispatch(loginSuccess(true));
};

export const login = (credentials) => ({
    return: dispatch => {
        dispatch(loading(true));
        return Http.post('/oauth/token', {
            grant_type: 'password',
            client_id: '',
            client_secret: '',
            username: credentials.username,
            password: credentials.password
        }).then(res => {
            dispatch(loading(false));
            if (typeof res !== 'undefined') {
                dispatch(setUserToken(res.data.access_token));
            }
        });
    }
});