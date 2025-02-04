package org.naderica.metrix.component.message

enum ErrorStrategy:
    case 
        /** 
         * When an error occurs, stops on the error point 
         * */
        StopOnError, 
        /**
          * Continues the process even if an error occurs
          */
        ContinueOnError