export class GeneralResponse<T> {
  data?: T;
  success?: boolean;
  message?: string;
  errorCode?: number;
  messageResult?: string;
}
