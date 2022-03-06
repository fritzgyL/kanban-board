import { Injectable } from "@angular/core";
import { NgbDateAdapter, NgbDateStruct } from "@ng-bootstrap/ng-bootstrap";

@Injectable({
    providedIn: 'root'
})
export class CustomDatePickerAdapter extends NgbDateAdapter<string> {

    readonly DELIMITER1 = '-';
    readonly DELIMITER2 = '/';


    fromModel(value: string | null): NgbDateStruct | null {
        if (value) {
            const date = value.split(this.DELIMITER1);
            return {
                day: parseInt(date[2], 10),
                month: parseInt(date[1], 10),
                year: parseInt(date[0], 10)
            };
        }
        return null;
    }

    toModel(date: NgbDateStruct | null): string | null {
        return date ? date.year + this.DELIMITER1 + date.month + this.DELIMITER1 + date.day : null;
    }

    parse(value: string): NgbDateStruct | null {
        if (value) {
            const date = value.split(this.DELIMITER1);
            return {
                day: parseInt(date[2], 10),
                month: parseInt(date[1], 10),
                year: parseInt(date[0], 10)
            };
        }
        return null;
    }

    format(date: NgbDateStruct | null): string {
        return date ? date.day + this.DELIMITER2 + date.month + this.DELIMITER2 + date.year : '';
    }
}