import { configureStore } from '@reduxjs/toolkit';
import {api} from "../api/api.js";
import cartSlice from "../reducers/cartSlice.js";


export const store = configureStore({
    reducer: {
        [api.reducerPath]: api.reducer,
        cart: cartSlice

    },
    middleware: (getDefaultMiddleware) =>
        getDefaultMiddleware().concat(api.middleware),
});