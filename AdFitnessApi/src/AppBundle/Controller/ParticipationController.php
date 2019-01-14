<?php

namespace AppBundle\Controller;

use AppBundle\Entity\Participation;
use AppBundle\Entity\Session;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\JsonResponse;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\Serializer\Normalizer\ObjectNormalizer;
use Symfony\Component\Serializer\Serializer;

class ParticipationController extends Controller
{
    public function createAction(Request $request)
    {
        $p = new Participation();
        $em = $this->getDoctrine()->getManager();
        $p->setSession($em->getRepository("AppBundle:Session")->find($request->get('session')));
        $p->getSession()->setCurrentNb($p->getSession()->getCurrentNb()+1);
        $em->persist($p->getSession());
        $em->flush();
        $p->setUser($em->getRepository("AppBundle:User")->find($request->get('user')));
        $em->persist($p);
        $em->flush();
        if(is_null($p))
            return new JsonResponse("false");
        else return new JsonResponse("true");
    }
    public function removeAction(Request $request)
    {
        $p = new Participation();
        $em = $this->getDoctrine()->getManager();
        $session = $em->getRepository("AppBundle:Session")->find($request->get('session'));
        $user = $em->getRepository("AppBundle:User")->find($request->get('user'));
        $p->setSession($session);
        $p->getSession()->setCurrentNb($p->getSession()->getCurrentNb()-1);
        $em->persist($p->getSession());
        $em->flush();



        $p  = $em->getRepository("AppBundle:Participation")->findOneBy(array('user'=>$user,'session'=>$session));
        $em->remove($p);
        $em->flush();


        if(is_null($p))
            return new JsonResponse("false");
        else return new JsonResponse("true");
    }

    public function myParticipationAction(Request $request)
    {
        $em = $this->getDoctrine()->getManager();
        $user = $em->getRepository("AppBundle:User")->find($request->get('user'));
        $ListPart = $em->getRepository("AppBundle:Participation")->findBy(array('user'=>$user));
        $session=array();
        foreach($ListPart as $p)
        {
            $id = $p->getSession()->getId();
            $s=$em->getRepository("AppBundle:Session")->find($id);
            $s->setDate($s->getDate()->format('Y-m-d H:i'));
            array_push($session,$s);
         /*   $p->getSession()->setDate($p->getSession()->getDate());
            array_push($session,$p->getSession());*/
        }
        $serializer=new Serializer([new ObjectNormalizer()]);
        $formated=$serializer->normalize($session);
        return new JsonResponse($formated);
    }
    public function myParticipationCountAction(Request $request)
    {
        $em = $this->getDoctrine()->getManager();
        $user = $em->getRepository("AppBundle:User")->find($request->get('user'));
        $ListPart = $em->getRepository("AppBundle:Participation")->findBy(array('user'=>$user));
        $serializer=new Serializer([new ObjectNormalizer()]);
        $formated=$serializer->normalize(count($ListPart));
        return new JsonResponse($formated);
    }
}
