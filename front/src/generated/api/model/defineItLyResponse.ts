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
import { UserWithPoints } from './userWithPoints';
import { User } from './user';


export interface DefineItLyResponse { 
    uuid?: string;
    response?: string;
    responseByUser?: User;
    chosenBy?: Array<UserWithPoints>;
}

