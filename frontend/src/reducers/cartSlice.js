import { createSlice } from '@reduxjs/toolkit';

const cartSlice = createSlice({
    name: 'cart',
    initialState: {
        cart: {items: [], allowedMissingSlots: null},
    },
    reducers: {
        setCart: (state, action) => {
            state.cart = action.payload;
        },
    },
});

export const {
    setCart,
} = cartSlice.actions;

export const selectCart = (state) => state.cart.cart;
// export const selectCart = (state) => ({
//     items: [...state.cart.cart.items], // Создаем новый массив
//     allowedMissingSlots: state.cart.cart.allowedMissingSlots,
// });

export default cartSlice.reducer;
