export interface ProductModel {
  product_ammount: number;
  product_number: number;
  product_state: string;
  product_type: string;
  client_id: ClientID;
}

interface ClientID {
  client_id: number;
}
