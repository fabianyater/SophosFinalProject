export interface OperationModel {
  operation_id:          number;
  operation_type:        string;
  operation_date:        string;
  operation_value:       number;
  operation_description: string;
  account_number:        number;
  product_id:            ProductID;
}

interface ProductID {
  product_id: number;
}
