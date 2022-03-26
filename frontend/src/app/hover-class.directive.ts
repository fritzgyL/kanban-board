import { Directive, HostListener, ElementRef, Input } from '@angular/core';

@Directive({
    selector: '[hover-class]'
})
export class HoverClassDirective {

    constructor(public elementRef: ElementRef) { }
    @Input('hover-class') hoverClasses: any;

    @HostListener('mouseenter') onMouseEnter() {
        const classes = this.hoverClasses.split(' ');
        classes.forEach((item: any) => this.elementRef.nativeElement.classList.add(item));
    }

    @HostListener('mouseleave') onMouseLeave() {
        const classes = this.hoverClasses.split(' ');
        classes.forEach((item: any) => this.elementRef.nativeElement.classList.remove(item));
    }

}
