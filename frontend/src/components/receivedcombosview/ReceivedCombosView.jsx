import {usePickUpComboMutation} from "../../api/api.js";
import {useSelector} from "react-redux";
import {selectCart} from "../../reducers/cartSlice.js";
import {useEffect} from "react";
import {Card, CardContent, CardHeader, Grid, LinearProgress, List, ListItem, Paper, Typography} from "@mui/material";
import {Box} from "@mui/system";

const ReceivedCombosView = () => {
    const [pickUpTrigger, {data, error, isFetching, isLoading}] = usePickUpComboMutation();
    const cart = useSelector(selectCart);


    useEffect(() => {
        if (cart.items.length > 0) {
            pickUpTrigger({items: cart.items, allowedMissingSlots: cart.allowedMissingSlots})
        }
    }, [cart]);


    if (isLoading || isFetching) {
        return <LinearProgress sx={{position: "absolute", width: "100%", top: 0, left: 0}} />
    }

    if (!data) {
        return null;
    }

    return (
        <Box sx={{mt: 3}}>
            <Grid container spacing={3}>
                {data.map((order, orderIndex) => (
                    <Grid item xs={6} key={orderIndex}>
                        <Card>
                            <CardHeader
                                title={` Вариант ${orderIndex + 1}`}
                                subheader={`Цена: ${order.price} ₽`}
                            />
                            <CardContent>
                                {order.comboResults.map((comboResult, comboIndex) => (
                                    <Card key={comboIndex}
                                          sx={{
                                              mb: 2,
                                              boxShadow: 'none',
                                              borderLeft: 'none',
                                              borderRight: 'none',
                                              p: 0,
                                              width: '100%'
                                            }}
                                          variant={"outlined"}
                                    >
                                        <CardContent sx={{ p: 0 }}>
                                            <Typography variant="h6">{comboResult.combo.title}</Typography>
                                            <Typography color="textSecondary">
                                                Цена комбо: {comboResult.finalComboPrice} ₽
                                            </Typography>
                                            <Typography variant="body2" sx={{ mt: 1 }}>
                                                {comboResult.usedItems.map((item) => item.generalMenu.title).join(", ")}
                                            </Typography>
                                        </CardContent>
                                    </Card>
                                ))}

                                {order.standaloneItems.length > 0 && (
                                    <Card
                                        sx={{
                                            boxShadow: 'none',
                                            borderLeft: 'none',
                                            borderRight: 'none',
                                            p: 0,
                                            width: '100%'
                                        }}
                                        variant={"outlined"}
                                    >
                                        <CardContent sx={{ p: 0 }}>
                                            <Typography variant="h6" color="textSecondary">Отдельные товары</Typography>
                                            {order.standaloneItems.map((item) => (
                                                <Typography key={item.id} variant="body2">
                                                    {item.category} - {item.title} ({item.price} ₽)
                                                </Typography>
                                            ))}
                                        </CardContent>
                                    </Card>
                                )}
                            </CardContent>
                        </Card>
                    </Grid>
                ))}
            </Grid>
        </Box>
    );
}
export default ReceivedCombosView;