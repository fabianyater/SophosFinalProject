export interface ProductModel {
  product_id: number;
  product_ammount: number;
  product_number: number;
  product_state: string;
  product_type: string;
  customer_id: CustomerID;
}

interface CustomerID {
  customer_id: number;
}


export interface nose{
  product_id: number;
  product_state: string
}
