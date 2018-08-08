/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { DemoTestModule } from '../../../test.module';
import { SolicitudesDeleteDialogComponent } from 'app/entities/solicitudes/solicitudes-delete-dialog.component';
import { SolicitudesService } from 'app/entities/solicitudes/solicitudes.service';

describe('Component Tests', () => {
    describe('Solicitudes Management Delete Component', () => {
        let comp: SolicitudesDeleteDialogComponent;
        let fixture: ComponentFixture<SolicitudesDeleteDialogComponent>;
        let service: SolicitudesService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [DemoTestModule],
                declarations: [SolicitudesDeleteDialogComponent]
            })
                .overrideTemplate(SolicitudesDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(SolicitudesDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(SolicitudesService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('confirmDelete', () => {
            it('Should call delete service on confirmDelete', inject(
                [],
                fakeAsync(() => {
                    // GIVEN
                    spyOn(service, 'delete').and.returnValue(of({}));

                    // WHEN
                    comp.confirmDelete(123);
                    tick();

                    // THEN
                    expect(service.delete).toHaveBeenCalledWith(123);
                    expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
                })
            ));
        });
    });
});
