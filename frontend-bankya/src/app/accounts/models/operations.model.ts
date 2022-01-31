export interface OperationModel {
  operation_id:          number;
  operation_type:        string;
  operation_date:        string;
  operation_value:       number;
  operation_description: string;
  operation_balance:     number;
  account_number:        number;
  product_id:            ProductID;
}

interface ProductID {
  product_id: number;
  product_ammount: number;
}
