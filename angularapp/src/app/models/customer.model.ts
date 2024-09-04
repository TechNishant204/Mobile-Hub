import { User } from "./user.model";

export interface Customer {
    customerId?:number; 

    customerName?:string;

    address?:string;
    
    user?:User;
}
