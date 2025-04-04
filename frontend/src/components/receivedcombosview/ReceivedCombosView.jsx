import {usePickUpComboMutation} from "../../api/api.js";
import {useSelector} from "react-redux";
import {selectCart} from "../../reducers/cartSlice.js";
import {useEffect} from "react";
import {Card, CardContent, Grid, Typography} from "@mui/material";

const ReceivedCombosView = () => {
    const [pickUpTrigger, {data, error, isFetching}] = usePickUpComboMutation();
    const cart = useSelector(selectCart);
    console.log(cart)

    useEffect(() => {
        if (cart.items.length > 0) {
            pickUpTrigger({items: cart.items, allowedMissingSlots: cart.allowedMissingSlots})
        }
    }, [cart]);

    if (!data) {
        return <div>Ничего еще</div>;
    }


    return (
        <Grid container spacing={3}>
            {data.map((order, orderIndex) => (
                <Grid item xs={12} key={orderIndex}>
                    <Typography variant="h5" gutterBottom>
                        Заказ {orderIndex + 1} - Общая цена: {order.price} ₽
                    </Typography>

                    {order.comboResults.map((comboResult, comboIndex) => (
                        <Card key={comboIndex} sx={{ mb: 2 }}>
                            <CardContent>
                                <Typography variant="h6">{comboResult.combo.title}</Typography>
                                <Typography color="textSecondary">
                                    Цена комбо: {comboResult.finalComboPrice} ₽
                                </Typography>

                                <Typography variant="body2" sx={{ mt: 1 }}>
                                    <strong>Использованные элементы (gmenuId):</strong>{" "}
                                    {comboResult.usedItems.map((item) => item.gmenuId).join(", ")}
                                </Typography>
                            </CardContent>
                        </Card>
                    ))}

                    {order.standaloneItems.length > 0 && (
                        <Card sx={{ mt: 2 }}>
                            <CardContent>
                                <Typography variant="h6">Отдельные товары</Typography>
                                {order.standaloneItems.map((item) => (
                                    <Typography key={item.id} variant="body2">
                                        {item.category} - {item.title} ({item.price} ₽)
                                    </Typography>
                                ))}
                            </CardContent>
                        </Card>
                    )}
                </Grid>
            ))}
        </Grid>
    );
}
export default ReceivedCombosView;