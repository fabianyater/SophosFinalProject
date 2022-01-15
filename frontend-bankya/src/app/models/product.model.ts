export interface ProductModel {
  product_id: number;
  product_ammount: number;
  product_number: number;
  product_state: string;
  product_type: string;
  client_id: ClientID;
}

interface ClientID {
  client_id: number;
}


export interface nose{
  product_id: number;
  product_state: string
}