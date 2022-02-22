import { actionTypes } from '../actions/auth.action'

export const initialState = {
    auth: {
        token: {
            username: '',
            password: ''
        },
        isLoading: {
            active: false,
            message: null
        },
        success: false,
        error: null
    }
}
export const authReducer = (state = initialState.auth, action) => {
    switch (action.type) {
        case actionTypes.CHANGE:
            return {...state,
                credentials: {
                    ...state.credentials,
                    ...action.payload
                }
            }
        case actionTypes.GET_TOKEN:
            return {
                ...state,
                ...action.token
            }
        default:
            return state;
    }
}