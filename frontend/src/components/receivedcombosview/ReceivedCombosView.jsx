import {usePickUpComboMutation} from "../../api/api.js";
import {useSelector} from "react-redux";
import {selectCart} from "../../reducers/cartSlice.js";
import {useEffect, useRef} from "react";
import {
    Card,
    CardContent,
    CardHeader,
    Divider,
    Grid,
    LinearProgress,
    Typography
} from "@mui/material";
import {Box} from "@mui/system";

const ReceivedCombosView = () => {
    const [pickUpTrigger, {data, error, isFetching, isLoading}] = usePickUpComboMutation();
    const cart = useSelector(selectCart);
    const contentRef = useRef(null);


    useEffect(() => {
        if (cart.items.length > 0) {
            pickUpTrigger({items: cart.items, allowedMissingSlots: cart.allowedMissingSlots})
        }
    }, [cart]);

    useEffect(() => {
        if (data && contentRef.current) {
            contentRef.current.scrollIntoView({ behavior: "smooth" }); // Плавная прокрутка
        }
    }, [data]);


    if (isLoading || isFetching) {
        return <LinearProgress sx={{position: "fixed", width: "100%", height: "3px", top: 0, left: 0}} />
    }

    if (!data) {
        return null;
    }

    return (
        <Box sx={{mt: 3}} ref={contentRef}>
            <Grid container spacing={2}>
                {data.map((order, orderIndex) => (
                    <Grid item xs={12} sm={6} key={orderIndex}>
                        <Card sx={{pb: 0}}>
                            <CardHeader
                                title={` Вариант ${orderIndex + 1}`}
                                subheader={`Цена: ${order.price} ₽`}
                            />
                            <CardContent sx={{paddingY: 0}}>
                                <Divider sx={{ mb: 1 }} />
                                {order.comboResults.map((comboResult, comboIndex) => (
                                    <Card key={comboIndex}
                                          sx={{
                                              boxShadow: 'none',
                                              borderLeft: 'none',
                                              borderRight: 'none',
                                              // padding: "10px 0 0 0",
                                              pt: "10px",
                                              // p: 0,
                                              width: '100%'
                                            }}
                                          variant={"outlined"}
                                          square={true}
                                    >
                                        <CardContent sx={{ p: 0 }}>
                                            <Typography variant="h6">{comboResult.combo.title}</Typography>
                                            <Typography color="textSecondary">
                                                Цена комбо: {comboResult.finalComboPrice} ₽
                                            </Typography>
                                            {comboResult.usedItems.map((item) => <ItemView item={item.generalMenu} lineThough={true}/> )}
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
                                            pt: "10px",
                                            width: '100%'
                                        }}
                                        variant={"outlined"}
                                    >
                                        <CardContent sx={{ p: 0 }}>
                                            <Typography variant="h6" color="textSecondary">Отдельные товары</Typography>
                                            {order.standaloneItems.map((item) => <ItemView item={item}/>)}
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


const ItemView = ({item, lineThough}) => {
    return (
        <Typography key={item.id} variant="body2">
            {item.category} - {item.title} (
            <Typography component={"span"}
                        color={lineThough && "textSecondary"}
                        sx={lineThough && {textDecoration: 'line-through' }}
            >
                {item.price}
            </Typography>
            ₽)
        </Typography>
    )
}

export default ReceivedCombosView;