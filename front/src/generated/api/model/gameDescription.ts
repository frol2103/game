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


export interface GameDescription { 
    id?: number;
    uuid?: string;
    startTime?: Date;
    status?: GameDescription.StatusEnum;
    gameType?: GameDescription.GameTypeEnum;
}
export namespace GameDescription {
    export type StatusEnum = 'toStart' | 'inPlay' | 'finished';
    export const StatusEnum = {
        ToStart: 'toStart' as StatusEnum,
        InPlay: 'inPlay' as StatusEnum,
        Finished: 'finished' as StatusEnum
    };
    export type GameTypeEnum = 'lostInTranslation';
    export const GameTypeEnum = {
        LostInTranslation: 'lostInTranslation' as GameTypeEnum
    };
}


