import { Customer } from "./customer.model";
import { Mobile } from "./mobile.model";

export interface Order {
    orderId?: number;

    orderPrice?: number;

    quantity?: number;
    
    mobiles?: Mobile[];
    customer?: Customer;

     dateOrdered?: Date;
}
