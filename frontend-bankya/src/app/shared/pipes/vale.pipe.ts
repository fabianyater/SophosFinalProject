import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'vale',
})
export class ValePipe implements PipeTransform {
  public message: string = '';

  transform(value: unknown, ...args: unknown[]) {
    if (value === 'A') {
      this.message = 'Inactive';
    }
    if (value === 'I') {
      this.message = 'Active';
    }
    return this.message;
  }
}
