export * from './auth.service';
import { AuthService } from './auth.service';
export * from './esquisser.service';
import { EsquisserService } from './esquisser.service';
export * from './game.service';
import { GameService } from './game.service';
export const APIS = [AuthService, EsquisserService, GameService];
