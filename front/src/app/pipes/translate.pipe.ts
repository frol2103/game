import {Pipe, PipeTransform} from '@angular/core';
import {LabelsService} from "../services/labels.service";

@Pipe({
    name: 'translate'
})
export class TranslatePipe implements PipeTransform {

    constructor(private labelsService: LabelsService) {
    }

    transform(value: string, ...args: unknown[]): string {
        return this.labelsService.translate(value)
    }

}
