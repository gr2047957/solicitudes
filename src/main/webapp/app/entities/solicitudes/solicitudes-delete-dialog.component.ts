import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ISolicitudes } from 'app/shared/model/solicitudes.model';
import { SolicitudesService } from './solicitudes.service';

@Component({
    selector: 'jhi-solicitudes-delete-dialog',
    templateUrl: './solicitudes-delete-dialog.component.html'
})
export class SolicitudesDeleteDialogComponent {
    solicitudes: ISolicitudes;

    constructor(
        private solicitudesService: SolicitudesService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.solicitudesService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'solicitudesListModification',
                content: 'Deleted an solicitudes'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-solicitudes-delete-popup',
    template: ''
})
export class SolicitudesDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ solicitudes }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(SolicitudesDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.solicitudes = solicitudes;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
                        this.ngbModalRef = null;
                    }
                );
            }, 0);
        });
    }

    ngOnDestroy() {
        this.ngbModalRef = null;
    }
}
