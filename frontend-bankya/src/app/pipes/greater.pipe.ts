import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'greater',
})
export class GreaterPipe implements PipeTransform {
  public message: string = '';

  transform(value: unknown, ...args: unknown[]) {
    if (value == 'Deposit') {
      this.message = '+';
    }
    if (value == 'Withdraw' || value == 'Transfer') {
      this.message = '-';
    }
    
    console.log("Pipe: ", this.message);
    return this.message;
  }
}
