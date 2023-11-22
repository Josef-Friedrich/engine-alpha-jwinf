declare const modulesPath: string | undefined;

declare const strings: { messages: Messages };

declare function quickAlgoContext(display: any, infos: any): any;

interface Window {
  modulesPath?: string;
  languageStrings: {
    messages: Messages;
  };

  quickAlgoResponsive: boolean;
}

interface Messages {
  leavesGrid: string;
  obstacle: string;
  nothingToPickUp: string;
  nothingToLookAt: string;
  falls: string;
  willFallAndCrash: string;
  jumpOutsideGrid: string;
  jumpObstacleBlocking: string;
  jumpNoPlatform: string;
  tooManyObjects: string;
  emptyBag: string;
  successReachExit: string;
  failureReachExit: string;
  successPickedAllWithdrawables: string;
  failurePickedAllWithdrawables: string;
  successContainersFilled: string;
  failureContainersFilled: string;
  failureContainersFilledLess: string;
  failureContainersFilledBag: string;
  failureUnfilteredObject: string;
  failureTooManyMoves: string;
  failureWriteHere: string;
  failureReadHere: string;
  successNumbersWritten: string;
  failureNumbersWritten: string;
  failureNothingToPush: string;
  failureWhilePushing: string;
  failureDropObject: string;
  failureDropPlatform: string;
  failureDropOutside: string;
  failureNotEnoughPlatform: string;
  failureLights: string;
  successLights: string;
  failureLaser: string;
  successPlugsWired: string;
  failurePlugsWired: string;
}
