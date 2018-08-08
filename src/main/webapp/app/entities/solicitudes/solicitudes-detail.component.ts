import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ISolicitudes } from 'app/shared/model/solicitudes.model';

@Component({
    selector: 'jhi-solicitudes-detail',
    templateUrl: './solicitudes-detail.component.html'
})
export class SolicitudesDetailComponent implements OnInit {
    solicitudes: ISolicitudes;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ solicitudes }) => {
            this.solicitudes = solicitudes;
        });
    }

    previousState() {
        window.history.back();
    }
}
