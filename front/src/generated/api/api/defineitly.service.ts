/**
 * Game
 * online game platform
 *
 * The version of the OpenAPI document: 1.0.0
 * Contact: game@frol.be
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */
/* tslint:disable:no-unused-variable member-ordering */

import { Inject, Injectable, Optional }                      from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams,
         HttpResponse, HttpEvent, HttpParameterCodec }       from '@angular/common/http';
import { CustomHttpParameterCodec }                          from '../encoder';
import { Observable }                                        from 'rxjs';

import { DefineItLyGame } from '../model/defineItLyGame';

import { BASE_PATH, COLLECTION_FORMATS }                     from '../variables';
import { Configuration }                                     from '../configuration';



@Injectable({
  providedIn: 'root'
})
export class DefineitlyService {

    protected basePath = 'https://game.frol.be/api';
    public defaultHeaders = new HttpHeaders();
    public configuration = new Configuration();
    public encoder: HttpParameterCodec;

    constructor(protected httpClient: HttpClient, @Optional()@Inject(BASE_PATH) basePath: string, @Optional() configuration: Configuration) {
        if (configuration) {
            this.configuration = configuration;
        }
        if (typeof this.configuration.basePath !== 'string') {
            if (typeof basePath !== 'string') {
                basePath = this.basePath;
            }
            this.configuration.basePath = basePath;
        }
        this.encoder = this.configuration.encoder || new CustomHttpParameterCodec();
    }



    /**
     * Add a definitely question
     * @param uuid 
     * @param text the question
     * @param observe set whether or not to return the data Observable as the body, response or events. defaults to returning the body.
     * @param reportProgress flag to report request and response progress.
     */
    public addQuestion(uuid: string, text: string, observe?: 'body', reportProgress?: boolean): Observable<DefineItLyGame>;
    public addQuestion(uuid: string, text: string, observe?: 'response', reportProgress?: boolean): Observable<HttpResponse<DefineItLyGame>>;
    public addQuestion(uuid: string, text: string, observe?: 'events', reportProgress?: boolean): Observable<HttpEvent<DefineItLyGame>>;
    public addQuestion(uuid: string, text: string, observe: any = 'body', reportProgress: boolean = false ): Observable<any> {
        if (uuid === null || uuid === undefined) {
            throw new Error('Required parameter uuid was null or undefined when calling addQuestion.');
        }
        if (text === null || text === undefined) {
            throw new Error('Required parameter text was null or undefined when calling addQuestion.');
        }

        let headers = this.defaultHeaders;

        // to determine the Accept header
        const httpHeaderAccepts: string[] = [
            'application/json'
        ];
        const httpHeaderAcceptSelected: string | undefined = this.configuration.selectHeaderAccept(httpHeaderAccepts);
        if (httpHeaderAcceptSelected !== undefined) {
            headers = headers.set('Accept', httpHeaderAcceptSelected);
        }


        // to determine the Content-Type header
        const consumes: string[] = [
            'application/json'
        ];
        const httpContentTypeSelected: string | undefined = this.configuration.selectHeaderContentType(consumes);
        if (httpContentTypeSelected !== undefined) {
            headers = headers.set('Content-Type', httpContentTypeSelected);
        }

        return this.httpClient.post<DefineItLyGame>(`${this.configuration.basePath}/game/DefineItLy/${encodeURIComponent(String(uuid))}/question`,
            text,
            {
                withCredentials: this.configuration.withCredentials,
                headers: headers,
                observe: observe,
                reportProgress: reportProgress
            }
        );
    }

    /**
     * Add a definitely response to a question
     * @param uuid 
     * @param questionUuid 
     * @param text the response
     * @param observe set whether or not to return the data Observable as the body, response or events. defaults to returning the body.
     * @param reportProgress flag to report request and response progress.
     */
    public addResponse(uuid: string, questionUuid: string, text: string, observe?: 'body', reportProgress?: boolean): Observable<DefineItLyGame>;
    public addResponse(uuid: string, questionUuid: string, text: string, observe?: 'response', reportProgress?: boolean): Observable<HttpResponse<DefineItLyGame>>;
    public addResponse(uuid: string, questionUuid: string, text: string, observe?: 'events', reportProgress?: boolean): Observable<HttpEvent<DefineItLyGame>>;
    public addResponse(uuid: string, questionUuid: string, text: string, observe: any = 'body', reportProgress: boolean = false ): Observable<any> {
        if (uuid === null || uuid === undefined) {
            throw new Error('Required parameter uuid was null or undefined when calling addResponse.');
        }
        if (questionUuid === null || questionUuid === undefined) {
            throw new Error('Required parameter questionUuid was null or undefined when calling addResponse.');
        }
        if (text === null || text === undefined) {
            throw new Error('Required parameter text was null or undefined when calling addResponse.');
        }

        let headers = this.defaultHeaders;

        // to determine the Accept header
        const httpHeaderAccepts: string[] = [
            'application/json'
        ];
        const httpHeaderAcceptSelected: string | undefined = this.configuration.selectHeaderAccept(httpHeaderAccepts);
        if (httpHeaderAcceptSelected !== undefined) {
            headers = headers.set('Accept', httpHeaderAcceptSelected);
        }


        // to determine the Content-Type header
        const consumes: string[] = [
            'application/json'
        ];
        const httpContentTypeSelected: string | undefined = this.configuration.selectHeaderContentType(consumes);
        if (httpContentTypeSelected !== undefined) {
            headers = headers.set('Content-Type', httpContentTypeSelected);
        }

        return this.httpClient.post<DefineItLyGame>(`${this.configuration.basePath}/game/DefineItLy/${encodeURIComponent(String(uuid))}/question/${encodeURIComponent(String(questionUuid))}`,
            text,
            {
                withCredentials: this.configuration.withCredentials,
                headers: headers,
                observe: observe,
                reportProgress: reportProgress
            }
        );
    }

    /**
     * Chose a response to a question
     * @param uuid 
     * @param questionUuid 
     * @param responseUuid the response uuid
     * @param observe set whether or not to return the data Observable as the body, response or events. defaults to returning the body.
     * @param reportProgress flag to report request and response progress.
     */
    public choseResponse(uuid: string, questionUuid: string, responseUuid: string, observe?: 'body', reportProgress?: boolean): Observable<DefineItLyGame>;
    public choseResponse(uuid: string, questionUuid: string, responseUuid: string, observe?: 'response', reportProgress?: boolean): Observable<HttpResponse<DefineItLyGame>>;
    public choseResponse(uuid: string, questionUuid: string, responseUuid: string, observe?: 'events', reportProgress?: boolean): Observable<HttpEvent<DefineItLyGame>>;
    public choseResponse(uuid: string, questionUuid: string, responseUuid: string, observe: any = 'body', reportProgress: boolean = false ): Observable<any> {
        if (uuid === null || uuid === undefined) {
            throw new Error('Required parameter uuid was null or undefined when calling choseResponse.');
        }
        if (questionUuid === null || questionUuid === undefined) {
            throw new Error('Required parameter questionUuid was null or undefined when calling choseResponse.');
        }
        if (responseUuid === null || responseUuid === undefined) {
            throw new Error('Required parameter responseUuid was null or undefined when calling choseResponse.');
        }

        let headers = this.defaultHeaders;

        // to determine the Accept header
        const httpHeaderAccepts: string[] = [
            'application/json'
        ];
        const httpHeaderAcceptSelected: string | undefined = this.configuration.selectHeaderAccept(httpHeaderAccepts);
        if (httpHeaderAcceptSelected !== undefined) {
            headers = headers.set('Accept', httpHeaderAcceptSelected);
        }


        // to determine the Content-Type header
        const consumes: string[] = [
            'application/json'
        ];
        const httpContentTypeSelected: string | undefined = this.configuration.selectHeaderContentType(consumes);
        if (httpContentTypeSelected !== undefined) {
            headers = headers.set('Content-Type', httpContentTypeSelected);
        }

        return this.httpClient.post<DefineItLyGame>(`${this.configuration.basePath}/game/DefineItLy/${encodeURIComponent(String(uuid))}/question/${encodeURIComponent(String(questionUuid))}/choice`,
            responseUuid,
            {
                withCredentials: this.configuration.withCredentials,
                headers: headers,
                observe: observe,
                reportProgress: reportProgress
            }
        );
    }

    /**
     * Get a DefineItLy game
     * @param uuid 
     * @param observe set whether or not to return the data Observable as the body, response or events. defaults to returning the body.
     * @param reportProgress flag to report request and response progress.
     */
    public getGame(uuid: string, observe?: 'body', reportProgress?: boolean): Observable<DefineItLyGame>;
    public getGame(uuid: string, observe?: 'response', reportProgress?: boolean): Observable<HttpResponse<DefineItLyGame>>;
    public getGame(uuid: string, observe?: 'events', reportProgress?: boolean): Observable<HttpEvent<DefineItLyGame>>;
    public getGame(uuid: string, observe: any = 'body', reportProgress: boolean = false ): Observable<any> {
        if (uuid === null || uuid === undefined) {
            throw new Error('Required parameter uuid was null or undefined when calling getGame.');
        }

        let headers = this.defaultHeaders;

        // to determine the Accept header
        const httpHeaderAccepts: string[] = [
            'application/json'
        ];
        const httpHeaderAcceptSelected: string | undefined = this.configuration.selectHeaderAccept(httpHeaderAccepts);
        if (httpHeaderAcceptSelected !== undefined) {
            headers = headers.set('Accept', httpHeaderAcceptSelected);
        }


        return this.httpClient.get<DefineItLyGame>(`${this.configuration.basePath}/game/DefineItLy/${encodeURIComponent(String(uuid))}`,
            {
                withCredentials: this.configuration.withCredentials,
                headers: headers,
                observe: observe,
                reportProgress: reportProgress
            }
        );
    }

}
