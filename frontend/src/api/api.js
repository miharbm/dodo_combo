import { createApi, fetchBaseQuery } from '@reduxjs/toolkit/query/react';

const baseUrl = import.meta.env.VITE_API_URL


export const api = createApi({
    reducerPath: 'api',
    baseQuery: fetchBaseQuery({
        baseUrl: baseUrl,
    }),
    endpoints: (builder) => ({
        getGeneralMenu: builder.query({
            query: () => '/general-menu',
        }),
    }),
});

export const {
    useGetGeneralMenuQuery,
} = api;
