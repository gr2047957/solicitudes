/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { DemoTestModule } from '../../../test.module';
import { SolicitudesDetailComponent } from 'app/entities/solicitudes/solicitudes-detail.component';
import { Solicitudes } from 'app/shared/model/solicitudes.model';

describe('Component Tests', () => {
    describe('Solicitudes Management Detail Component', () => {
        let comp: SolicitudesDetailComponent;
        let fixture: ComponentFixture<SolicitudesDetailComponent>;
        const route = ({ data: of({ solicitudes: new Solicitudes(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [DemoTestModule],
                declarations: [SolicitudesDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(SolicitudesDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(SolicitudesDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.solicitudes).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
