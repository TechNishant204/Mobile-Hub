import { Customer } from "./customer.model";

export interface Review {

    
    reviewId?:number;

    customer?:Customer;

    subject?:string;
    
    body?:string;

    rating?:number;
    
    dateCreated?:Date;
}
