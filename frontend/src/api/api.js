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
        pickUpCombo: builder.mutation({
            query: ({items, allowedMissingSlots}) => {
                const body = items.map(item => ({
                    id: item.id,
                    count: item.count,
                }))

                return {
                    url: "/combo/pick-up",
                    method: "POST",
                    body: body,
                    params: allowedMissingSlots === null ? {} : {
                        allowedMissingSlots: allowedMissingSlots,
                    }
                }
            },
        })
    }),
});

export const {
    useGetGeneralMenuQuery,
    usePickUpComboMutation,
} = api;
